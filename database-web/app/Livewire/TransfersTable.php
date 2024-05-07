<?php

namespace App\Livewire;

use Livewire\Component;
use App\Models\Transfer;

class TransfersTable extends Component
{
    public function render()
    {
        return view('livewire.transfers-table', ["transfers" => Transfer::all()]);
    }
}
