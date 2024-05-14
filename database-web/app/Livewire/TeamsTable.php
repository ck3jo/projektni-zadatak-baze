<?php

namespace App\Livewire;

use Livewire\Component;
use App\Models\Team;

class TeamsTable extends Component
{
    public $nameSearch = "";
    public $regionSearch = "";
    public $sortBy = "";
    public $sortDir = "";

    public function setSortBy($sortCol)
    {
        if ($this->sortBy == $sortCol) 
        { 
            $this->sortDir = ($this->sortDir == "ASC") ? "DESC" : "ASC"; 
            return;
        }
        else
        {
            $this->sortBy = $sortCol;  
            $this->sortDir = "ASC";
        }
    }

    public function render()
    {
        return view('livewire.teams-table', [
            'teams' => Team::where("ImeTima", "LIKE", "%". $this->nameSearch ."%")
                            ->when($this->regionSearch !== "", function ($query) {
                                $query->where("Region", "=", $this->regionSearch);
                            })
                            ->when($this->sortBy !== "" && $this->sortDir !== "", function ($query) {
                                $query->orderBy($this->sortBy, $this->sortDir);
                            })
                            ->get()
        ]);
    }
}
