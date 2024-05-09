<?php

namespace App\Livewire;

use Livewire\Component;
use App\Models\Tournament;

class TournamentsTable extends Component
{
    public $nameSearch = "";
    public $lowerStartDateSearch = "";
    public $lowerEndDateSearch = "";
    public $upperStartDateSearch = "";
    public $upperEndDateSearch = "";
    public $locationSearch = "";
    public $bigTournamentSearch = "";

    public function render()
    {
        return view('livewire.tournaments-table', [
            "tournaments" => Tournament::where("Ime", "LIKE", "%".$this->nameSearch."%")
                                        ->when($this->lowerStartDateSearch !== "" && $this->upperStartDateSearch === "", function($query) {
                                            $query->whereDate("DatumPocetka", ">", $this->lowerStartDateSearch);
                                        })
                                        ->when($this->upperStartDateSearch !== "" && $this->lowerStartDateSearch === "", function($query) {
                                            $query->whereDate("DatumPocetka", "<", $this->upperStartDateSearch);
                                        })
                                        ->when($this->lowerStartDateSearch !== "" && $this->upperStartDateSearch !== "", function($query) {
                                            $query->whereBetween("DatumPocetka", [ $this->lowerStartDateSearch, $this->upperStartDateSearch ]);
                                        })
                                        ->when($this->lowerEndDateSearch !== "" && $this->upperEndDateSearch === "", function($query) {
                                            $query->whereDate("DatumZavrsetka", ">", $this->lowerEndDateSearch);
                                        })
                                        ->when($this->upperEndDateSearch !== "" && $this->lowerEndDateSearch === "", function($query) {
                                            $query->whereDate("DatumZavrsetka", "<", $this->upperEndDateSearch);
                                        })
                                        ->when($this->lowerEndDateSearch !== "" && $this->upperEndDateSearch !== "", function($query) {
                                            $query->whereBetween("DatumZavrsetka", [ $this->lowerEndDateSearch, $this->upperEndDateSearch ]);
                                        })
                                        ->when($this->locationSearch !== "", function($query) {
                                            $query->where("MestoIgranja", "LIKE", "%". $this->locationSearch ."%");
                                        })
                                        ->when($this->bigTournamentSearch !== "", function($query) {
                                            $query->where("VeciTurnir", "=", $this->bigTournamentSearch );
                                        })
                                        ->get()
        ]);
    }
}
