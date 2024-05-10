<?php

namespace App\Livewire;

use Livewire\Component;
use App\Models\Team;

class TeamsTable extends Component
{
    public $nameSearch = "";

    public function render()
    {
        return view('livewire.teams-table', [
            'teams' => Team::where("ImeTima", "LIKE", $this->nameSearch)
                            ->get()
        ]);
    }
}
