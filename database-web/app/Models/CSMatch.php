<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Support\Facades\DB;

class CSMatch extends Model
{
    use HasFactory;

    protected $table = "mecevi";
    protected $primaryKey = "IDMeca";
    public $timestamps = false;

    protected $fillable = [
        "PrviTim",
        "DrugiTim",
        "IDTurnira",
        "BrojMapa",
        "Rezultat",
        "DatumMeca",
    ];

    public function getFirstTeamName()
    {
        return DB::scalar("SELECT ImeTima FROM timovi WHERE IDTima = ". $this->PrviTim);
    }

    public function getSecondTeamName()
    {
        return DB::scalar("SELECT ImeTima FROM timovi WHERE IDTima = ". $this->DrugiTim);
    }

    public function getTournamentName()
    {
        return DB::scalar("SELECT Ime FROM turniri WHERE IDTurnira = ". $this->IDTurnira);
    }
}
