<?php

namespace App\Livewire;

use Livewire\Component;
use App\Models\Player;
use App\Models\Team;

class PlayersTable extends Component
{
    public $nameSearch = "";
    public $nickSearch = "";
    public $surnameSearch = "";
    public $lowerDateSearch;
    public $upperDateSearch;
    public $nationalitySearch = "";

    public function render()
    {
        return view('livewire.players-table', [
            'players' => Player::where('Ime','LIKE','%'. $this->nameSearch .'%')
                               ->when($this->nickSearch !== "", function ($query) {
                                    $query->where("Nadimak","LIKE","%". $this->nickSearch ."%");
                               })
                               ->when($this->surnameSearch !== "", function ($query) {
                                    $query->where("Prezime","LIKE","%". $this->surnameSearch ."%");
                               })
                               ->when($this->lowerDateSearch !== null && $this->upperDateSearch === null, function ($query) {
                                    $query->whereDate("DatumRodjenja", ">", $this->lowerDateSearch);
                               })
                               ->when($this->upperDateSearch !== null && $this->lowerDateSearch === null, function ($query) {
                                    $query->whereDate("DatumRodjenja", "<", $this->upperDateSearch);
                               })
                               ->when($this->lowerDateSearch !== null && $this->upperDateSearch !== null, function ($query) {
                                    $query->whereBetween("DatumRodjenja", [$this->lowerDateSearch, $this->upperDateSearch]);
                               })
                               ->when($this->nationalitySearch !== "", function($query) {
                                    $query->where("Nacionalnost","LIKE","%". $this->nationalitySearch ."%");
                               })
                               ->get()
        ]);
    }
}
