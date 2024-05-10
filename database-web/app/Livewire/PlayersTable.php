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
                                   ->when($this->sortBy !== "" && $this->sortDir !== "", function ($query) {
                                        $query->orderBy($this->sortBy, $this->sortDir);
                                   })
                                   ->get(),
               'teams' => Team::all()
          ]);
     }
}
