<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\MorphMany; 
use Illuminate\Support\Facades\DB;
use Carbon\Carbon;

class Transfer extends Model
{
    use HasFactory;

    protected $table = "transferi";
    protected $primaryKey = "IDTransfera";

    protected $fillable = [
        "IDIgraca",
        "IDStarogTima",
        "IDNovogTima",
        "DatumTransfera"
    ];

    public function getFormattedDate()
    {
        return Carbon::parse($this->DatumTransfera)->toFormattedDateString();
    }

    public function getPlayerName()
    {
        return DB::scalar("SELECT Nadimak FROM igraci WHERE IDIgraca = ". $this->IDIgraca);
    }

    public function getOldTeamName()
    {
        return DB::scalar("SELECT ImeTima FROM timovi WHERE IDTima = ". $this->IDStarogTima);
    }

    public function getNewTeamName()
    {
        return DB::scalar("SELECT ImeTima FROM timovi WHERE IDTima = ". $this->IDNovogTima);
    }
}
