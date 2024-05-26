<?php

namespace App\Livewire;

use Livewire\Component;
use Illuminate\Support\Facades\DB;

class TeamsAverageAge extends Component
{
    public function getTeamName($teamID)
    {
        return DB::scalar("SELECT ImeTima FROM timovi WHERE IDTima = ". $teamID);
    }

    public function render()
    {
        return view('livewire.teams-average-age', ['teamsAvgAge' => DB::select('SELECT IDTima, ROUND(AVG(DATEDIFF(NOW(), igraci.DatumRodjenja)) / 365, 1) AS ProsecneGodine FROM igraci GROUP BY IDTima;')]);
    }
}