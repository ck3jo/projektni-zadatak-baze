<?php

namespace App\Livewire;

use Livewire\Component;
use Illuminate\Support\Facades\DB;
use App\Models\News;
use App\Models\Author;

class NewsTable extends Component
{
    public $titleSearch = "";
    public $lowerDateSearch = "";
    public $upperDateSearch = "";
    public $authorSearch = "";
    public $sortBy = "";
    public $sortDir = "";

    public function resetFilters()
    {
        $this->titleSearch = "";
        $this->lowerDateSearch = "";
        $this->upperDateSearch = "";
        $this->authorSearch = "";
        $this->sortBy = "";
        $this->sortDir = "";
    }

    public function setSortBy($sortCol)
    {
        if ($this->sortBy == $sortCol) 
        { 
            $this->sortDir = ($this->sortDir == "ASC") ? "DESC" : "ASC"; 
            return;
        }
        else
        {
            $this->sortBy = $sortCol;  
            $this->sortDir = "ASC";
        }
    }

    public function getAuthorID()
    {
        return DB::scalar("SELECT IDAutora FROM autori WHERE Nadimak = ". "'". $this->authorSearch ."'");
    }

    public function render()
    {
        return view('livewire.news-table', [
            'newsarr' => News::where("Naslov", "LIKE", "%".$this->titleSearch."%")
                             ->when($this->lowerDateSearch !== "" && $this->upperDateSearch === "", function ($query) {
                                $query->whereDate("DatumObjavljivanja", ">=", $this->lowerDateSearch);
                             })
                             ->when($this->upperDateSearch !== "" && $this->lowerDateSearch !== "", function ($query) {
                                $query->whereDate("DatumObjavljivanja", "<=", $this->upperDateSearch);
                             })
                             ->when($this->lowerDateSearch !== "" && $this->upperDateSearch !== "", function ($query) {
                                $query->whereBetween("DatumObjavljivanja", [ $this->lowerDateSearch, $this->upperDateSearch ]);
                             })
                             ->when($this->authorSearch !== "", function ($query) {
                                $query->where("IDAutora", "=", $this->getAuthorID());
                             })
                             ->when($this->sortBy !== "" && $this->sortDir !== "", function ($query) {
                                $query->orderBy($this->sortBy, $this->sortDir);
                             })
                             ->get(),
            'authors' => Author::all()
        ]);
    }
}
