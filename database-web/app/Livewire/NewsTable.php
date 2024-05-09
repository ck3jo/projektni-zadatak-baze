<?php

namespace App\Livewire;

use Livewire\Component;
use Illuminate\Support\Facades\DB;
use App\Models\News;
use App\Models\Author;

class NewsTable extends Component
{
    public $titleSearch = "";
    public $lowerDateSearch = null;
    public $upperDateSearch = null;
    public $authorSearch = "";

    public function getAuthorID()
    {
        return DB::scalar("SELECT IDAutora FROM autori WHERE Nadimak = ". "'". $this->authorSearch ."'");
    }

    public function render()
    {
        return view('livewire.news-table', [
            'newsarr' => News::where("Naslov", "LIKE", "%".$this->titleSearch."%")
                             ->when($this->lowerDateSearch !== null && $this->upperDateSearch === null, function ($query) {
                                $query->whereDate("DatumObjavljivanja", ">=", $this->lowerDateSearch);
                             })
                             ->when($this->upperDateSearch !== null && $this->lowerDateSearch !== null, function ($query) {
                                $query->whereDate("DatumObjavljivanja", "<=", $this->upperDateSearch);
                             })
                             ->when($this->lowerDateSearch !== null && $this->upperDateSearch !== null, function ($query) {
                                $query->whereBetween("DatumObjavljivanja", [ $this->lowerDateSearch, $this->upperDateSearch ]);
                             })
                             ->when($this->authorSearch !== "", function ($query) {
                                $query->where("IDAutora", "=", $this->getAuthorID());
                             })
                             ->get(),
            'authors' => Author::all()
        ]);
    }
}
