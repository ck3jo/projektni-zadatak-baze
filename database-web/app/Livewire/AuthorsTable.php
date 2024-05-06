<?php

namespace App\Livewire;

use Livewire\Component;
use App\Models\Author;

class AuthorsTable extends Component
{
    public function render()
    {
        return view('livewire.authors-table', ['authors' => Author::all()]);
    }
}
