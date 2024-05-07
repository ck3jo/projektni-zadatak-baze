<?php

namespace App\Livewire;

use Livewire\Component;
use App\Models\Tournament;

class TournamentsTable extends Component
{
    public function render()
    {
        return view('livewire.tournaments-table', ["tournaments" => Tournament::all()]);
    }
}
