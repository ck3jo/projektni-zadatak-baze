<?php

namespace App\Livewire;

use Livewire\Component;
use App\Models\News;

class NewsTable extends Component
{
    public function render()
    {
        return view('livewire.news-table', ['newsarr' => News::all()]);
    }
}
