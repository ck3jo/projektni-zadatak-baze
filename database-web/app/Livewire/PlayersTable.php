<?php

namespace App\Livewire;

use Livewire\Component;
use App\Models\Player;
use App\Models\Team;

class PlayersTable extends Component
{
    public function render()
    {
        return view('livewire.players-table', ['players' => Player::all(), 'teams' => Team::all()]);
    }
}
