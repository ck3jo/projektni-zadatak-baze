<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Support\Facades\DB;

class Coach extends Model
{
    use HasFactory;

    protected $table = "treneri";
    protected $primaryKey = "IDTrenera";
    public $timestamps = false;

    protected $fillable = [
        "Ime",
        "Nadimak",
        "Prezime",
        "IDTima"
    ];

    public function getTeamName()
    {
        return DB::scalar("SELECT ImeTima FROM timovi WHERE IDTima = ". $this->IDTima);
    }
}
