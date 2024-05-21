<?php

namespace App\Livewire;

use Livewire\Component;
use Illuminate\Support\Number;
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
    public $minPrizePool = 1000;
    public $maxPrizePool = 10000000;
    public $sortBy = "";
    public $sortDir = "";

    public function resetFilters()
    {
        $this->nameSearch = "";
        $this->lowerStartDateSearch = "";
        $this->lowerEndDateSearch = "";
        $this->upperStartDateSearch = "";
        $this->upperEndDateSearch = "";
        $this->locationSearch = "";
        $this->bigTournamentSearch = "";
        $this->minPrizePool = 1000;
        $this->maxPrizePool = 10000000;
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

    public function getFormattedMinPrizePool()
    {
        return Number::currency($this->minPrizePool, "USD");
    }

    public function getFormattedMaxPrizePool()
    {
        return Number::currency($this->maxPrizePool, "USD");
    }

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
                                        ->when($this->minPrizePool !== 1000 && $this->maxPrizePool === 10000000, function ($query) {
                                            $query->where("NagradniFond", ">=", $this->minPrizePool);
                                        })
                                        ->when($this->maxPrizePool !== 10000000 && $this->minPrizePool === 1000, function ($query) {
                                            $query->where("NagradniFond", "<=", $this->maxPrizePool);
                                        })
                                        ->when($this->minPrizePool !== 1000 && $this->maxPrizePool !== 10000000, function ($query) {
                                            $query->whereBetween("NagradniFond", [$this->minPrizePool, $this->maxPrizePool]);
                                        })
                                        ->when($this->sortBy !== "" && $this->sortDir !== "", function ($query) {
                                            $query->orderBy($this->sortBy, $this->sortDir);
                                        })
                                        ->get()
        ]);
    }
}
