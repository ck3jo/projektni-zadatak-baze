<div>
    <div>
        
    </div>
    <div class="flex justify-center py-10 px-10 rounded-t-lg overflow-hidden">
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
            <tbody class="[&>*:nth-child(odd)]:bg-gray-700 [&>*:nth-child(even)]:bg-gray-600">
                @foreach ($players as $player)
                    <tr class="text-white text-center">
                        <td class="py-2">{{ $player->Ime }}</td>
                        <td class="py-2">{{ $player->Nadimak }}</td>
                        <td class="py-2">{{ $player->Prezime }}</td>
                        <td class="py-2">{{ $player->DatumRodjenja }}</td>
                        <td class="py-2">{{ $player->Nacionalnost }}</td>
                        <td class="py-2">
                            @if ($player->IDTima == null)
                                Nije u timu
                            @else
                                {{ $player->team->ImeTima }}
                            @endif
                        </td>
                        <td class="py-2 {{ $player->Rejting >= 1.01 ? "text-green-700 font-bold" : "text-red-600" }}">{{ $player->Rejting }}</td>
                        <td class="py-2">{{ $player->MajorTrofeji }}</td>
                        <td class="py-2">{{ $player->MajorMVP }}</td>
                    </tr>
                @endforeach
            </tbody>
        </table>
    </div>
</div>