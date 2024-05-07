<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;
use Carbon\Carbon;

class News extends Model
{
    use HasFactory;

    protected $table = "Vesti";
    protected $primaryKey = "IDVesti";
    public $timestamps = false;

    protected $fillable = [
        "Naslov",
        "DatumObjavljivanja",
        "IDAutora"
    ];

    public function getFormattedDate()
    {
      return Carbon::parse($this->DatumObjavljivanja)->toFormattedDateString();
    }

    public function author() : BelongsTo
    {
        return $this->belongsTo(Author::class, "IDAutora");
    }
}
