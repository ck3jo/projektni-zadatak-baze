<div class="block mx-10">
    <div class="rounded-xl flex flex-row flex-wrap justify-center items-center mt-8 py-6 w-5/6 px-5 gap-x-10 gap-y-5 mx-auto text-white bg-gray-800">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6">
            <path fill-rule="evenodd" d="M10.5 3.75a6.75 6.75 0 1 0 0 13.5 6.75 6.75 0 0 0 0-13.5ZM2.25 10.5a8.25 8.25 0 1 1 14.59 5.28l4.69 4.69a.75.75 0 1 1-1.06 1.06l-4.69-4.69A8.25 8.25 0 0 1 2.25 10.5Z" clip-rule="evenodd" />
        </svg> 

        <select wire:model.live="firstTeamSearch" class="rounded-full py-2 px-2 bg-gray-700" id="firstTeamSelect">
            @foreach ($teams as $team)
                <option value="{{ $team->ImeTima }}">{{ $team->ImeTima }}</option>
            @endforeach
            <option selected value="" class="text-gray-500">Ime prvog tima</option>
        </select>
        <select wire:model.live="secondTeamSearch" class="rounded-full py-2 px-2 bg-gray-700" id="secondTeamSelect"> 
            @foreach ($teams as $team)
                <option value="{{ $team->ImeTima }}">{{ $team->ImeTima }}</option>
            @endforeach
            <option selected value="" class="text-gray-500">Ime drugog tima</option>
        </select>
        <select wire:model.live="tournamentSearch" class="rounded-full py-2 px-2 bg-gray-700" id="tournamentSearch">
            @foreach ($tournaments as $tournament)
                <option value="{{ $tournament->Ime }}">{{ $tournament->Ime }}</option>
            @endforeach
            <option selected value="" class="text-gray-500">Ime turnira</option>
        </select>
        <div class="flex flex-row gap-x-4 items-center">
            <p>Granice za broj mapa</p>
            <div class="flex flex-col gap-y-0">
                <p class="text-xs text-center">
                    @if ($this->minNumMaps === "1")
                        Nema
                    @else
                        {{ $this->minNumMaps }}
                    @endif
                </p>
                <input wire:model.live="minNumMaps" class="bg-fuchsia-950" value="1" min="1" max="6" step="1" type="range">
                <p class="text-xs text-center">Minimum</p>
            </div>
            <div class="flex flex-col gap-y-0">
                <p class="text-xs text-center">
                    @if ($this->maxNumMaps === "6")
                        Nema
                    @else
                        {{ $this->maxNumMaps }}
                    @endif
                </p>
                <input wire:model.live="maxNumMaps" value="6" min="2" max="6" step="1" type="range">
                <p class="text-xs text-center">Maksimum</p>
            </div>
        </div>
        <select wire:model.live="scoreSearch" class="bg-gray-700 rounded-full px-2 py-1">
            <option value="2:0">2:0</option>
            <option value="2:1">2:1</option>
            <option value="3:0">3:0</option>
            <option value="3:1">3:1</option>
            <option value="3:2">3:2</option>
            <option selected value="">Rezultat</option>
        </select>
        <div class="flex flex-row gap-x-4 items-center">
            <p>Granice za datum meča</p>
            <div class="flex flex-col gap-y-0">
                <input wire:model.blur="lowerDateSearch" class="rounded-full px-2 py-1 bg-gray-700" type="date" id="lowerDateSelect">
                <p class="font-2xs text-center">Donja</p>
            </div>
            <div class="flex flex-col gap-y-0">
                <input wire:model.blur="upperDateSearch" class="rounded-full px-2 py-1 bg-gray-700" type="date" id="lowerDateSelect">
                <p class="font-2xs text-center">Gornja</p>
            </div>
        </div>
        <button wire:click="resetFilters()" class="bg-gray-700 rounded-full px-2 py-1 text-white text-center">Resetuj filtere</button>
    </div>
    <div class="flex justify-center py-2 rounded-lg overflow-hidden">
        <table class="px-5 pt-2 w-5/6 rounded-lg">
            <thead class="bg-gray-800">
                <th wire:click="setSortBy('ImePrvogTima')" class="rounded-tl-xl py-3 font-bold text-white">
                    Ime prvog tima
                    <button>
                        @if ($sortBy !== "ImePrvogTima")
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="pt-2 w-5 h-5">
                                <path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15 12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9" />
                            </svg>
                        @elseif ($sortDir === "ASC")
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="pt-2 w-5 h-5">
                                <path stroke-linecap="round" stroke-linejoin="round" d="m4.5 15.75 7.5-7.5 7.5 7.5" />
                            </svg>
                        @else
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="pt-2 w-5 h-5">
                                <path stroke-linecap="round" stroke-linejoin="round" d="m19.5 8.25-7.5 7.5-7.5-7.5" />
                            </svg>
                        @endif
                    </button>   
                </th>
                <th wire:click="setSortBy('ImeDrugogTima')"  class="py-3 font-bold text-white">
                    Ime drugog tima
                    <button>
                        @if ($sortBy !== "ImeDrugogTima")
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="pt-2 w-5 h-5">
                                <path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15 12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9" />
                            </svg>
                        @elseif ($sortDir === "ASC")
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="pt-2 w-5 h-5">
                                <path stroke-linecap="round" stroke-linejoin="round" d="m4.5 15.75 7.5-7.5 7.5 7.5" />
                            </svg>
                        @else
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="pt-2 w-5 h-5">
                                <path stroke-linecap="round" stroke-linejoin="round" d="m19.5 8.25-7.5 7.5-7.5-7.5" />
                            </svg>
                        @endif
                    </button>   
                </th>
                <th wire:click="setSortBy('ImeTurnira')" class="py-3 font-bold text-white">
                    Ime turnira
                    <button>
                        @if ($sortBy !== "ImeTurnira")
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="pt-2 w-5 h-5">
                                <path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15 12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9" />
                            </svg>
                        @elseif ($sortDir === "ASC")
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="pt-2 w-5 h-5">
                                <path stroke-linecap="round" stroke-linejoin="round" d="m4.5 15.75 7.5-7.5 7.5 7.5" />
                            </svg>
                        @else
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="pt-2 w-5 h-5">
                                <path stroke-linecap="round" stroke-linejoin="round" d="m19.5 8.25-7.5 7.5-7.5-7.5" />
                            </svg>
                        @endif
                    </button>   
                </th>
                <th wire:click="setSortBy('BrojMapa')" class="py-3 font-bold text-white">
                    Broj mapa
                    <button>
                        @if ($sortBy !== "BrojMapa")
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="pt-2 w-5 h-5">
                                <path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15 12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9" />
                            </svg>
                        @elseif ($sortDir === "ASC")
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="pt-2 w-5 h-5">
                                <path stroke-linecap="round" stroke-linejoin="round" d="m4.5 15.75 7.5-7.5 7.5 7.5" />
                            </svg>
                        @else
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="pt-2 w-5 h-5">
                                <path stroke-linecap="round" stroke-linejoin="round" d="m19.5 8.25-7.5 7.5-7.5-7.5" />
                            </svg>
                        @endif
                    </button>  
                </th>
                <th wire:click="setSortBy('Rezultat')" class="py-3 font-bold text-white">
                    Rezultat
                    <button>
                        @if ($sortBy !== "Rezultat")
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="pt-2 w-5 h-5">
                                <path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15 12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9" />
                            </svg>
                        @elseif ($sortDir === "ASC")
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="pt-2 w-5 h-5">
                                <path stroke-linecap="round" stroke-linejoin="round" d="m4.5 15.75 7.5-7.5 7.5 7.5" />
                            </svg>
                        @else
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="pt-2 w-5 h-5">
                                <path stroke-linecap="round" stroke-linejoin="round" d="m19.5 8.25-7.5 7.5-7.5-7.5" />
                            </svg>
                        @endif
                    </button>  
                </th>
                <th wire:click="setSortBy('DatumMeca')" class="rounded-tr-xl py-3 font-bold text-white">
                    Datum meča
                    <button>
                        @if ($sortBy !== "DatumMeca")
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="pt-2 w-5 h-5">
                                <path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15 12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9" />
                            </svg>
                        @elseif ($sortDir === "ASC")
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="pt-2 w-5 h-5">
                                <path stroke-linecap="round" stroke-linejoin="round" d="m4.5 15.75 7.5-7.5 7.5 7.5" />
                            </svg>
                        @else
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="pt-2 w-5 h-5">
                                <path stroke-linecap="round" stroke-linejoin="round" d="m19.5 8.25-7.5 7.5-7.5-7.5" />
                            </svg>
                        @endif
                    </button>  
                </th>
            </thead>
            <tbody class="[&>*:nth-child(odd)]:bg-gray-700 [&>*:nth-child(even)]:bg-gray-600 [&>*:last-child]:rounded-b-lg">
                @foreach ($matches as $match)
                    <tr class="text-white text-center">
                        <td class="py-2">{{ $match->getFirstTeamName() }}</td>
                        <td class="py-2">{{ $match->getSecondTeamName() }}</td>
                        <td class="py-2">{{ $match->getTournamentName() }}</td>
                        <td class="py-2">{{ $match->BrojMapa }}</td>
                        <td class="py-2">{{ $match->Rezultat }}</td>
                        <td class="py-2">{{ $match->getFormattedDate() }}</td>
                    </tr>
                @endforeach
            </tbody>
        </table>
    </div>
</div>
