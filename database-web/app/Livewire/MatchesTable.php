<?php

namespace App\Livewire;

use Livewire\Component;
use App\Models\CSMatch;

class MatchesTable extends Component
{
    public function render()
    {
        return view('livewire.matches-table', ["matches" => CSMatch::all()]);
    }
}
