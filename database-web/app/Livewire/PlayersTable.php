<?php

namespace App\Livewire;

use Livewire\Component;
use App\Models\Player;
use App\Models\Team;
use Illuminate\Support\Facades\DB;

class PlayersTable extends Component
{
     public $nameSearch = "";
     public $nickSearch = "";
     public $surnameSearch = "";
     public $lowerDateSearch = "";
     public $upperDateSearch = "";
     public $nationalitySearch = "";
     public $teamNameSearch = "";
     public $minRating = "0";
     public $maxRating = "3";
     public $minMajorTrophies = "0";
     public $maxMajorTrophies = "10";
     public $minMajorMVPs = "0";
     public $maxMajorMVPs = "5";
     public $sortBy = "";
     public $sortDir = "";

     public function resetFilters()
     {
          $this->nameSearch = "";
          $this->nickSearch = "";
          $this->surnameSearch = "";
          $this->lowerDateSearch = "";
          $this->upperDateSearch = "";
          $this->nationalitySearch = "";
          $this->teamNameSearch = "";
          $this->minRating = "0";
          $this->maxRating = "3";
          $this->minMajorTrophies = "0";
          $this->maxMajorTrophies = "10";
          $this->minMajorMVPs = "0";
          $this->maxMajorMVPs = "5";
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

     public function getTeamID()
     {
          return DB::scalar("SELECT IDTima FROM timovi WHERE ImeTima = ". "'". $this->teamNameSearch ."'");
     }

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
                                   ->when($this->lowerDateSearch !== "" && $this->upperDateSearch === "", function ($query) {
                                        $query->whereDate("DatumRodjenja", ">", $this->lowerDateSearch);
                                   })
                                   ->when($this->upperDateSearch !== "" && $this->lowerDateSearch === "", function ($query) {
                                        $query->whereDate("DatumRodjenja", "<", $this->upperDateSearch);
                                   })
                                   ->when($this->lowerDateSearch !== "" && $this->upperDateSearch !== "", function ($query) {
                                        $query->whereBetween("DatumRodjenja", [$this->lowerDateSearch, $this->upperDateSearch]);
                                   })
                                   ->when($this->nationalitySearch !== "", function($query) {
                                        $query->where("Nacionalnost","LIKE","%". $this->nationalitySearch ."%");
                                   })
                                   ->when($this->teamNameSearch === "no-team", function($query) {
                                        $query->where("IDTima", "=", null);
                                   })
                                   ->when($this->teamNameSearch !== "", function($query) {
                                        $query->where("IDTima", "=", $this->getTeamID());
                                   })
                                   ->when($this->minRating !== "0" && $this->maxRating === "3", function($query) {
                                        $query->where("Rejting", ">", $this->minRating);
                                   })
                                   ->when($this->maxRating !== "3" && $this->minRating === "0", function($query) {
                                        $query->where("Rejting", "<", $this->maxRating);
                                   })
                                   ->when($this->minRating !== "0" && $this->maxRating !== "3", function($query) {
                                        $query->whereBetween("Rejting", [$this->minRating, $this->maxRating]);
                                   })
                                   ->when($this->minMajorTrophies !== "0" && $this->maxMajorTrophies === "10", function($query) {
                                        $query->where("MajorTrofeji", ">=", $this->minMajorTrophies);
                                   })
                                   ->when($this->maxMajorTrophies !== "10" && $this->minMajorTrophies === "0", function($query) {
                                        $query->where("MajorTrofeji", "<=", $this->maxMajorTrophies);
                                   })
                                   ->when($this->minMajorTrophies !== "0" && $this->maxMajorTrophies !== "10", function($query) {
                                        $query->whereBetween("MajorTrofeji", [$this->minMajorTrophies, $this->maxMajorTrophies]);
                                   })
                                   ->when($this->minMajorMVPs !== "0" && $this->maxMajorMVPs === "5", function($query) {
                                        $query->where("MajorMVP", ">=", $this->minMajorMVPs);
                                   })
                                   ->when($this->maxMajorMVPs !== "5" && $this->minMajorMVPs === "0", function($query) {
                                        $query->where("MajorMVP", "<=", $this->maxMajorMVPs);
                                   })
                                   ->when($this->minMajorMVPs !== "0" && $this->maxMajorMVPs !== "5", function($query) {
                                        $query->whereBetween("MajorMVP", [$this->minMajorMVPs, $this->maxMajorMVPs]);
                                   })
                                   ->when($this->sortBy !== "" && $this->sortDir !== "", function ($query) {
                                        $query->orderBy($this->sortBy, $this->sortDir);
                                   })
                                   ->get(),
               'teams' => Team::all()
          ]);
     }
}
