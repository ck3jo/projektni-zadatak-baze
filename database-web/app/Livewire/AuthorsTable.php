<?php

namespace App\Livewire;

use Livewire\Component;
use App\Models\Author;

class AuthorsTable extends Component
{
    public $nameSearch = "";
    public $nickSearch = "";
    public $surnameSearch = "";

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
                               ->get()
        ]);
    }
}
