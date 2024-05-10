<div class="block mx-10">
    <div class="rounded-xl flex flex-row flex-wrap mt-8 py-6 w-full gap-x-10 gap-y-5 text-white bg-gray-800 justify-center items-center">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6">
            <path fill-rule="evenodd" d="M10.5 3.75a6.75 6.75 0 1 0 0 13.5 6.75 6.75 0 0 0 0-13.5ZM2.25 10.5a8.25 8.25 0 1 1 14.59 5.28l4.69 4.69a.75.75 0 1 1-1.06 1.06l-4.69-4.69A8.25 8.25 0 0 1 2.25 10.5Z" clip-rule="evenodd" />
        </svg> 

        <input wire:model.live.debounce.150ms="nameSearch" class="placeholder:text-center rounded-full px-2 py-1 text-white bg-gray-700" type="text" placeholder="Ime igrača">
        <input wire:model.live.debounce.150ms="nickSearch" class="placeholder:text-center rounded-full px-2 py-1 text-white bg-gray-700" type="text" placeholder="Nadimak igrača">
        <input wire:model.live.debounce.150ms="surnameSearch" class="placeholder:text-center rounded-full px-2 py-1 text-white bg-gray-700" type="text" placeholder="Prezime igrača">
        <div class="flex flex-row gap-x-4 items-center">
            <p>Granice za datum rođenja</p>
            <div class="flex flex-col gap-x-0">
                <input wire:model.blur="lowerDateSearch" class="rounded-full px-2 py-1 bg-gray-700" type="date" id="lowerDateSelect">
                <p class="font-2xs text-center">Donja</p>
            </div>
            <div class="flex flex-col gap-x-0">
                <input wire:model.blur="upperDateSearch" class="rounded-full px-2 py-1 bg-gray-700" type="date" id="lowerDateSelect">
                <p class="font-2xs text-center">Gornja</p>
            </div>
        </div>
        <input wire:model.live.debounce.150ms="nationalitySearch" class="placeholder:text-center rounded-full px-2 py-1 text-white bg-gray-700" type="text" placeholder="Nacionalnost igrača">
        <div class="flex flex-row gap-x-4 items-center">
            <label for="teamSelect">Izabran tim</label>
            <select wire:model.live="teamNameSearch" class="rounded-full px-2 py-1 bg-gray-700" id="teamSelect" selected="Nijedan tim">
                <option selected value="">Nijedan tim</option>
                <option value="no-team">Nije u timu</option>
                @foreach ($teams as $team)
                    <option value="{{ $team->ImeTima }}">{{ $team->ImeTima }}</option>
                @endforeach
            </select>
        </div>
    </div>
    <div class="flex justify-center py-2 rounded-t-lg overflow-hidden">
        <table class="px-5 pt-10 w-full rounded-t-lg">
            <thead class="bg-gray-800">
                <th wire:click="setSortBy('Ime')" class="rounded-tl-xl py-3 font-bold text-white">
                    Ime
                    <button>
                        @if ($sortBy !== "Ime")
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
                <th wire:click="setSortBy('Nadimak')" class="py-3 font-bold text-white">
                    Nadimak
                    <button>
                        @if ($sortBy !== "Nadimak")
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
                <th wire:click="setSortBy('Prezime')" class="py-3 font-bold text-white">
                    Prezime
                    <button>
                        @if ($sortBy !== "Prezime")
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
                <th wire:click="setSortBy('DatumRodjenja')" class="py-3 font-bold text-white">
                    Datum rođenja
                    <button>
                        @if ($sortBy !== "DatumRodjenja")
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
                <th wire:click="setSortBy('Nacionalnost')" class="py-3 font-bold text-white">
                    Nacionalnost
                    <button>
                        @if ($sortBy !== "Nacionalnost")
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
                <th class="py-3 font-bold text-white">
                    Ime tima
                    <!-- todo later -->
                </th>
                <th wire:click="setSortBy('Rejting')" class="py-3 font-bold text-white">
                    Rejting
                    <button>
                        @if ($sortBy !== "Rejting")
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
                <th wire:click="setSortBy('MajorTrofeji')" class="py-3 font-bold text-white">
                    Major trofeji
                    <button>
                        @if ($sortBy !== "MajorTrofeji")
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
                <th wire:click="MajorMVP" class="rounded-tr-xl py-3 font-bold text-white text-wrap">
                    Major MVP medalje
                    <button>
                        @if ($sortBy !== "MajorMVP")
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
            <tbody class="[&>*:nth-child(odd)]:bg-gray-700 [&>*:nth-child(even)]:bg-gray-600 [&>*:last-child]:rounded-b-xl">
                @foreach ($players as $player)
                    <tr class="text-white text-center">
                        <td class="py-2">{{ $player->Ime }}</td>
                        <td class="py-2">{{ $player->Nadimak }}</td>
                        <td class="py-2">{{ $player->Prezime }}</td>
                        <td class="py-2">{{ $player->getFormattedDate($player->DatumRodjenja) }}</td>
                        <td class="py-2">{{ $player->Nacionalnost }}</td>
                        <td class="py-2">
                            @if ($player->IDTima == null)
                                Nije u timu
                            @else
                                {{ $player->team->ImeTima }}
                            @endif
                        </td>
                        <td @class([
                            "py-2", 
                            "text-green-500" => $player->Rejting > 1.05,
                            "text-orange-400" => $player->Rejting <= 1.05 && $player->Rejting >= 1.00,
                            "text-rose-400" => $player->Rejting < 1.00,
                            "font-bold" => $player->Rejting > 1.05 || $player->Rejting < 1.00,
                            ])>{{ $player->Rejting }}</td>
                        <td class="py-2">{{ $player->MajorTrofeji }}</td>
                        <td class="py-2">{{ $player->MajorMVP }}</td>
                    </tr>
                @endforeach
            </tbody>
        </table>
    </div>
</div>