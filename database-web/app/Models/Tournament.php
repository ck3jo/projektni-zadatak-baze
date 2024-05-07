<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\HasMany;
use Illuminate\Database\Eloquent\Relations\MorphMany;

class Tournament extends Model
{
    use HasFactory;

    protected $table = "turniri";
    protected $primaryKey = "IDTurnira";

    protected $fillable = [
        "Ime",
        "DatumPocetka",
        "DatumZavrsetka",
        "MestoIgranja",
        "NagradniFond",
        "VeciTurnir"
    ];

    public function match() : HasMany
    {
        return $this->hasMany(CSMatch::class, "IDTurnira");    
    }
}
