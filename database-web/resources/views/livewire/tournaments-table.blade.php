<div class="block mx-10">
    <div class="rounded-xl flex flex-row flex-wrap mt-8 py-6 mx-auto w-5/6 gap-x-10 gap-y-5 text-white bg-gray-800 justify-center items-center">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6">
            <path fill-rule="evenodd" d="M10.5 3.75a6.75 6.75 0 1 0 0 13.5 6.75 6.75 0 0 0 0-13.5ZM2.25 10.5a8.25 8.25 0 1 1 14.59 5.28l4.69 4.69a.75.75 0 1 1-1.06 1.06l-4.69-4.69A8.25 8.25 0 0 1 2.25 10.5Z" clip-rule="evenodd" />
        </svg> 

        <input wire:model.live.debounce.150ms="nameSearch" class="placeholder:text-center rounded-full px-2 py-1 text-white bg-gray-700" type="text" placeholder="Ime turnira">
        <div class="flex flex-row gap-x-4 items-center">
            <label for="lowerStartDatePicker">Donja granica početka</label>
            <input wire:model.live="lowerStartDateSearch" class="rounded-full px-2 py-1 bg-gray-700" type="date" id="lowerStartDatePicker">
        </div>
        <div class="flex flex-row gap-x-4 items-center">
            <label for="upperStartDatePicker">Gornja granica početka</label>
            <input wire:model.live="upperStartDateSearch" class="rounded-full px-2 py-1 bg-gray-700" type="date" id="upperStartDatePicker">
        </div>
        <div class="flex flex-row gap-x-4 items-center">
            <label for="lowerEndDatePicker">Donja granica završetka</label>
            <input wire:model.live="lowerEndDateSearch" class="rounded-full px-2 py-1 bg-gray-700" type="date" id="lowerEndDatePicker">
        </div>
        <div class="flex flex-row gap-x-4 items-center">
            <label for="upperEndDatePicker">Gornja granica završetka</label>
            <input wire:model.live="upperEndDateSearch" class="rounded-full px-2 py-1 bg-gray-700" type="date" id="upperEndDatePicker">
        </div>
        <input wire:model.live.debounce.150ms="locationSearch" type="text" class="placeholder:text-center rounded-full px-2 py-1 bg-gray-700" placeholder="Lokacija">
        <select wire:model.live="bigTournamentSearch" class="rounded-full px-2 py-1 bg-gray-700" id="bigTournamentBox">
            <option class="text-gray-500" value="">Veliki turnir?</option>
            <option value="0">Nije</option>
            <option value="1">Jeste</option>
        </select>
    </div>
    <div class="flex justify-center py-10 rounded-t-lg overflow-hidden">
        <table class="px-5 pt-10 w-5/6 rounded-t-lg">
            <thead class="bg-gray-800">
                <th class="rounded-tl-xl py-3 font-bold text-white">Ime turnira</th>
                <th class="py-3 font-bold text-white">Datum početka</th>
                <th class="py-3 font-bold text-white">Datum završetka</th>
                <th class="py-3 font-bold text-white">Mesto igranja</th>
                <th class="py-3 font-bold text-white">Nagradni fond</th>
                <th class="rounded-tr-xl py-3 font-bold text-white">Veći turnir?</th>
            </thead>
            <tbody class="[&>*:nth-child(odd)]:bg-gray-700 [&>*:nth-child(even)]:bg-gray-600">
                @foreach ($tournaments as $tournament)
                    <tr class="text-white text-center">
                        <td class="py-2">{{ $tournament->Ime }}</td>
                        <td class="py-2">{{ $tournament->getFormattedStartDate() }}</td>
                        <td class="py-2">{{ $tournament->getFormattedEndDate() }}</td>
                        <td class="py-2">{{ $tournament->MestoIgranja }}</td>
                        <td class="py-2">{{ $tournament->getFormattedPrizePool() }}</td>
                        <td class="py-2 text-white align-middle items-center justify-center">
                            @if ($tournament->VeciTurnir === 1)
                                <div class="flex justify-center">
                                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6 text-center">
                                        <path fill-rule="evenodd" d="M19.916 4.626a.75.75 0 0 1 .208 1.04l-9 13.5a.75.75 0 0 1-1.154.114l-6-6a.75.75 0 0 1 1.06-1.06l5.353 5.353 8.493-12.74a.75.75 0 0 1 1.04-.207Z" clip-rule="evenodd" />
                                    </svg>
                                </div>
                            @else
                                <div class="flex justify-center"    >
                                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6 text-center">
                                        <path fill-rule="evenodd" d="M5.47 5.47a.75.75 0 0 1 1.06 0L12 10.94l5.47-5.47a.75.75 0 1 1 1.06 1.06L13.06 12l5.47 5.47a.75.75 0 1 1-1.06 1.06L12 13.06l-5.47 5.47a.75.75 0 0 1-1.06-1.06L10.94 12 5.47 6.53a.75.75 0 0 1 0-1.06Z" clip-rule="evenodd" />
                                    </svg>
                                </div>
                            @endif
                        </td>
                    </tr>
                @endforeach
            </tbody>
        </table>
    </div>
</div>
