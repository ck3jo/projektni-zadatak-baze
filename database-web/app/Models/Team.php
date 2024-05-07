<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\HasMany;
use App\Models\Player;

class Team extends Model
{
    use HasFactory;

    protected $table = "timovi";
    protected $primaryKey = "IDTima";
    public $timestamps = false;

    protected $fillable = [
        "ImeTima",
        "RangPozicija",
        "MajorTrofeji",
        "Region"
    ];

    public function player(): HasMany
    {
        return $this->hasMany(Player::class, "IDIgraca");
    }
}
