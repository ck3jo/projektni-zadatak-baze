<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

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
}
