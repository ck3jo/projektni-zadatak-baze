<?php

namespace App\Livewire;

use Livewire\Component;
use App\Models\Author;

class AuthorsTable extends Component
{
    public $nameSearch = "";
    public $nickSearch = "";
    public $surnameSearch = "";
    public $sortBy = "";
    public $sortDir = "";

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

    public function render()
    {
        return view('livewire.authors-table', [
            'authors' => Author::where("Ime", "like", "%". $this->nameSearch ."%")
                               ->when($this->nickSearch != "", function ($query) {
                                    $query->where("Nadimak", "like", "%". $this->nickSearch ."%");
                               })
                               ->when($this->surnameSearch != "", function ($query) {
                                    $query->where("Prezime", "like", "%". $this->surnameSearch ."%");
                               })
                               ->when($this->sortBy !== "" && $this->sortDir !== "", function ($query) {
                                    $query->orderBy($this->sortBy, $this->sortDir);
                               })
                               ->get()
        ]);
    }
}
