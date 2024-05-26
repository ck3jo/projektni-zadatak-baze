<?php

use Illuminate\Support\Facades\Route;
use App\Livewire\ShowAuthors;

Route::get('/', function () {
    return view('home');
});

Route::get('/authors', function () {
    return view('authors');
});

Route::get('/players', function () {
    return view('players');
});

Route::get('/matches', function () {
    return view('matches');
});

Route::get('/teams', function() {
    return view('teams');
});

Route::get('/transfers', function () {
    return view('transfers');
});

Route::get('/coaches', function() {
    return view('coaches');
});

Route::get('/tournaments', function() {
    return view('tournaments');
});

Route::get('/news', function() {
    return view('news');
});

Route::get('/teams-average-age', function() {
    return view('teams-average-age');
});