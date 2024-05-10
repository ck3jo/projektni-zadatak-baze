<?php

namespace App\Livewire;

use Livewire\Component;
use App\Models\Transfer;
use App\Models\Team;
use Illuminate\Support\Facades\DB;

class TransfersTable extends Component
{
    public $playerSearch = "";
    public $oldTeamSearch = "";
    public $newTeamSearch = "";
    public $lowerDateSearch = "";
    public $upperDateSearch = "";

    public function getPlayerID()
    {
        return DB::scalar("SELECT IDIgraca FROM igraci WHERE Nadimak = ". "'". $this->playerSearch ."'");
    }

    public function getTeamID($oldNew)
    {
        if ($oldNew === "old")
        {
            return DB::scalar("SELECT IDTima FROM timovi WHERE ImeTima = ". "'". $this->oldTeamSearch ."'");
        }
        else if ($oldNew === "new")
        {
            return DB::scalar("SELECT IDTima FROM timovi WHERE ImeTima = ". "'". $this->newTeamSearch ."'");
        }
    }

    public function render()
    {
        return view('livewire.transfers-table', [
            "transfers" => Transfer::query()
                                    ->when($this->playerSearch !== "", function ($query) {
                                        $query->where("IDIgraca", $this->getPlayerID());
                                    })
                                    ->when($this->oldTeamSearch !== "", function ($query) {
                                        $query->where("IDStarogTima", $this->getTeamID("old"));
                                    })
                                    ->when($this->newTeamSearch !== "", function ($query) {
                                        $query->where("IDNovogTima", $this->getTeamID("new"));
                                    })
                                    ->when($this->lowerDateSearch !== "" && $this->upperDateSearch === "", function ($query) {
                                        $query->whereDate("DatumTransfera", ">", $this->lowerDateSearch);
                                    })
                                    ->when($this->upperDateSearch !== "" && $this->lowerDateSearch === "", function ($query) {
                                        $query->whereDate("DatumTransfera", "<",$this->upperDateSearch);
                                    })
                                    ->when($this->lowerDateSearch !== "" && $this->upperDateSearch !== "", function ($query) {
                                        $query->whereBetween("DatumTransfera", [$this->lowerDateSearch, $this->upperDateSearch]);
                                    })
                                    ->get(),
            "teams" => Team::all(),
        ]);
    }
}
