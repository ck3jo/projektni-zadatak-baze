<?php

namespace App\Livewire;

use Livewire\Component;
use Illuminate\Support\Facades\DB;
use App\Models\Tournament;
use App\Models\CSMatch;
use App\Models\Team;

class MatchesTable extends Component
{
    public $firstTeamSearch = "";
    public $secondTeamSearch = "";
    public $tournamentSearch = "";
    public $lowerDateSearch = "";
    public $upperDateSearch = "";

    public function triggerAlert() { if ($this->firstTeamSearch === $this->secondTeamSearch) { $this->firstTeamSearch = ""; }}

    public function getFirstTeamID()
    {
        $this->triggerAlert();
        return DB::scalar("SELECT IDTima FROM timovi WHERE ImeTima = ". "'". $this->firstTeamSearch ."'");
    }

    public function getSecondTeamID()
    {
        $this->triggerAlert();
        return DB::scalar("SELECT IDTima FROM timovi WHERE ImeTima = ". "'". $this->secondTeamSearch ."'");
    }

    public function getTournamentID()
    {
        return DB::scalar("SELECT IDTurnira FROM turniri WHERE Ime = ". "'". $this->tournamentSearch ."'");
    }

    public function render()
    {
        return view('livewire.matches-table', [
            "matches" => CSMatch::query()
                                ->when($this->secondTeamSearch !== "" && $this->firstTeamSearch === "", function ($query) {
                                    $query->where("DrugiTim", "=", $this->getSecondTeamID())
                                          ->orWhere("PrviTim","=", $this->getSecondTeamID());
                                })
                                ->when($this->firstTeamSearch !== "" && $this->secondTeamSearch === "", function ($query) {
                                    $query->where("PrviTim", "=", $this->getFirstTeamID())
                                          ->orWhere("DrugiTim","=", $this->getFirstTeamID());
                                })
                                ->when($this->firstTeamSearch !== "" && $this->secondTeamSearch !== "", function ($query) {
                                    $query->orWhere("PrviTim", "=", $this->getFirstTeamID())
                                          ->where("DrugiTim", "=", $this->getSecondTeamID())
                                          ->orWhere("DrugiTim", "=", $this->getSecondTeamID())
                                          ->where("PrviTim", "=", $this->getFirstTeamID());
                                })
                                ->when($this->tournamentSearch !== "", function ($query) {
                                    $query->where("IDTurnira", "=", $this->getTournamentID());
                                })
                                ->when($this->lowerDateSearch !== "" && $this->upperDateSearch === "", function ($query) {
                                    $query->whereDate("DatumMeca", ">", $this->lowerDateSearch);
                                })
                                ->when($this->upperDateSearch !== "" && $this->lowerDateSearch === "", function ($query) {
                                    $query->whereDate("DatumMeca", "<", $this->upperDateSearch);
                                })
                                ->when($this->lowerDateSearch !== "" && $this->upperDateSearch !== "", function ($query) {
                                    $query->whereBetween("DatumMeca", [$this->lowerDateSearch, $this->upperDateSearch]);
                                })
                                ->get(),
            "teams" => Team::all(),
            "tournaments" => Tournament::all()
        ]);
    }
}
