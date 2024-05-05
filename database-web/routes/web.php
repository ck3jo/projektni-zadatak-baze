<?php

use Illuminate\Support\Facades\Route;
use App\Livewire\ShowAuthors;

Route::get('/', function () {
    return view('welcome');
});

Route::get('/authors', [ShowAuthors::class, 'render']);
?>