<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

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
}
