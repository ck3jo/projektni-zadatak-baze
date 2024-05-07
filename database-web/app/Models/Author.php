<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\HasMany;

class Author extends Model
{
    use HasFactory;

    protected $table = "autori";
    protected $primaryKey = "IDAutora"; 
    public $timestamps = false;

    protected $fillable = [
        "Ime",
        "Nadimak",
        "Prezime"
    ];

    public function news() : HasMany
    {
        return $this->hasMany(News::class, "IDVesti");
    }
}
