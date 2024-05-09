<div class="block mx-10">
    <div class="rounded-xl flex flex-row flex-wrap mt-8 py-6 w-full gap-x-10 gap-y-5 text-white bg-gray-800 justify-center items-center">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6">
            <path fill-rule="evenodd" d="M10.5 3.75a6.75 6.75 0 1 0 0 13.5 6.75 6.75 0 0 0 0-13.5ZM2.25 10.5a8.25 8.25 0 1 1 14.59 5.28l4.69 4.69a.75.75 0 1 1-1.06 1.06l-4.69-4.69A8.25 8.25 0 0 1 2.25 10.5Z" clip-rule="evenodd" />
        </svg> 

        <input wire:model.live.debounce.150ms="nameSearch" class="placeholder:text-center rounded-full px-2 py-1 text-white bg-gray-700" type="text" placeholder="Ime igrača">
        <input wire:model.live.debounce.150ms="nickSearch" class="placeholder:text-center rounded-full px-2 py-1 text-white bg-gray-700" type="text" placeholder="Nadimak igrača">
        <input wire:model.live.debounce.150ms="surnameSearch" class="placeholder:text-center rounded-full px-2 py-1 text-white bg-gray-700" type="text" placeholder="Prezime igrača">
        <div class="flex flex-row gap-x-4 items-center">
            <label for="lowerDatePicker">Donja granica datuma rođenja</label>
            <input wire:model.blur="lowerDateSearch" class="rounded-full px-2 py-1 bg-gray-700" id="lowerDatePicker" type="date" placeholder="Donja granica datuma">
        </div>
        <div class="flex flex-row gap-x-4 items-center">
            <label for="upperDatePicker">Gornja granica datuma rođenja</label>
            <input wire:model.blur="upperDateSearch" class="rounded-full px-2 py-1 bg-gray-700" id="upperDatePicker" type="date" placeholder="Gornja granica datuma">
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
                <th class="rounded-tl-xl py-3 font-bold text-white">Ime</th>
                <th class="py-3 font-bold text-white">Nadimak</th>
                <th class="py-3 font-bold text-white">Prezime</th>
                <th class="py-3 font-bold text-white">Datum rođenja</th>
                <th class="py-3 font-bold text-white">Nacionalnost</th>
                <th class="py-3 font-bold text-white">Ime tima</th>
                <th class="py-3 font-bold text-white">Rejting</th>
                <th class="py-3 font-bold text-white">Major trofeji</th>
                <th class="rounded-tr-xl py-3 font-bold text-white">Major MVP</th>
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