<?php

namespace App\Livewire;

use Livewire\Component;
use App\Models\Coach;

class CoachesTable extends Component
{
    public $nameSearch = "";
    public $nickSearch = "";
    public $surnameSearch = "";

    public function render()
    {
        return view('livewire.coaches-table', [
            "coaches" => Coach::where("Ime", "like", "%". $this->nameSearch ."%")
                              ->when($this->nickSearch, function ($query) {
                                    $query->where("Nadimak","like", "%".$this->nickSearch ."%");
                              })
                              ->when($this->surnameSearch, function ($query) {
                                    $query->where("Prezime","like", "%". $this->surnameSearch ."%");
                              })
                              ->get()
                
        ]);
    }
}
