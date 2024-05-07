<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;
use App\Models\Team;
use Carbon\Carbon;

class Player extends Model
{
    use HasFactory;

    protected $table = "igraci";
    protected $primaryKey = "IDIgraca";
    public $timestamps = false;
  
    protected $fillable = [
      "Ime",
      "Nadimak",
      "Prezime",
      "DatumRodjenja",
      "Nacionalnost",
      "IDTima",
      "Rejting",
      "MajorTrofeji",
      "MajorMVP"  
    ];

    public function getFormattedDate()
    {
      return Carbon::parse($this->DatumRodjenja)->toFormattedDateString();
    }

    public function team() : BelongsTo
    {
      return $this->belongsTo(Team::class, "IDTima");
    }
}
