<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\HasMany;
use Illuminate\Support\Number;
use Carbon\Carbon;
use NumberFormatter;

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

    public function getFormattedStartDate()
    {
        return Carbon::parse($this->DatumPocetka)->toFormattedDateString();
    }

    public function getFormattedEndDate()
    {
        return Carbon::parse($this->DatumZavrsetka)->toFormattedDateString();
    }

    public function getFormattedPrizePool()
    {
        return Number::currency($this->NagradniFond, "USD");
    }

    public function match() : HasMany
    {
        return $this->hasMany(CSMatch::class, "IDTurnira");    
    }
}
