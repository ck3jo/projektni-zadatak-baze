<?php

namespace App\Livewire;

use Livewire\Component;
use App\Models\Team;

class TeamsTable extends Component
{
    public function render()
    {
        return view('livewire.teams-table', [
            'teams' => Team::all(),
        ]);
    }
}
