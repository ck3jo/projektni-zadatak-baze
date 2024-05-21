<?php

namespace App\Livewire;

use Livewire\Component;
use App\Models\Team;

class TeamsTable extends Component
{
    public $nameSearch = "";
    public $minRanking = "1";
    public $maxRanking = "31";
    public $minMajorTrophies = "0";
    public $maxMajorTrophies = "10";
    public $regionSearch = "";
    public $sortBy = "";
    public $sortDir = "";

    public function resetFilters()
    {
        $this->nameSearch = "";
        $this->minRanking = "1";
        $this->maxRanking = "31";
        $this->minMajorTrophies = "0";
        $this->maxMajorTrophies = "10";
        $this->regionSearch = "";
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

    public function render()
    {
        return view('livewire.teams-table', [
            'teams' => Team::where("ImeTima", "LIKE", "%". $this->nameSearch ."%")
                            ->when($this->minRanking !== "1" && $this->maxRanking === "31", function ($query) {
                                $query->where("RangPozicija", ">=", $this->minRanking);
                            })
                            ->when($this->maxRanking !== "31" && $this->minRanking === "1", function ($query) {
                                $query->where("RangPozicija", "<=", $this->maxRanking);
                            })
                            ->when($this->minRanking !== "1" && $this->maxRanking !== "31", function ($query) {
                                $query->whereBetween("RangPozicija", [$this->minRanking, $this->maxRanking]);
                            })
                            ->when($this->minMajorTrophies !== "1" && $this->maxMajorTrophies === "10", function ($query) {
                                $query->where("MajorTrofeji", ">=", $this->minMajorTrophies);
                            })
                            ->when($this->maxMajorTrophies !== "10" && $this->maxMajorTrophies === "0", function ($query) {
                                $query->where("MajorTrofeji", "<=", $this->maxMajorTrophies);
                            })
                            ->when($this->minMajorTrophies !== "0" && $this->maxMajorTrophies !== "10", function ($query) {
                                $query->whereBetween("MajorTrofeji", [$this->minMajorTrophies, $this->maxMajorTrophies]);
                            })
                            ->when($this->regionSearch !== "", function ($query) {
                                $query->where("Region", "=", $this->regionSearch);
                            })
                            ->when($this->sortBy !== "" && $this->sortDir !== "", function ($query) {
                                $query->orderBy($this->sortBy, $this->sortDir);
                            })
                            ->get()
        ]);
    }
}
