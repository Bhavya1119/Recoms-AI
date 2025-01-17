/**
* Created by Bhavya Joshi
*/

// Function to format genres as a comma-separated string
function formatGenres(genres) {
    return genres.join(", ");
}

// Function to display artist info in a presentable way
function formatArtistInfo(artistInfo) {
    return `
        <div class="artist-info">
            <p><strong>Name:</strong> ${artistInfo.name}</p>
            <p><strong>ID:</strong> ${artistInfo.id}</p>
            <p><strong>Genres:</strong> ${formatGenres(artistInfo.genres)}</p>
            <p><strong>Followers:</strong> ${artistInfo.followers}</p>
            <p><strong>Popularity:</strong> ${artistInfo.popularity}</p>
            <p><strong>URL:</strong> <a class="artist-url" href="${artistInfo.href}" target="_blank">${artistInfo.href}</a></p>
        </div>
    `;
}


function fetchTopArtists() {
    const topArtistsDetails = document.getElementById('topArtistsDetails');
    const errorMessage = document.getElementById('errorMessage');


    fetch('/topArtists')
        .then(response => response.json())
        .then(data => {
            if (!data || data.length === 0) {
                errorMessage.style.display = 'block';
                errorMessage.textContent = 'No top artists found';
                return;
            }

            let topArtistsHTML = `
                <table>
                    <thead>
                        <tr>
                            <th>Artist Name</th>
                            <th>Genres</th>
                            <th>Followers</th>
                            <th>Popularity</th>
                        </tr>
                    </thead>
                    <tbody>
            `;

            data.forEach(entry => {
                topArtistsHTML += `
                    <tr>
                        <td>${entry.artistInfo.name}</td>
                        <td>${formatGenres(entry.artistInfo.genres)}</td>
                        <td>${entry.artistInfo.followers}</td>
                        <td>${entry.artistInfo.popularity}</td>
                    </tr>
                `;
            });

            topArtistsHTML += `
                    </tbody>
                </table>
            `;

            topArtistsDetails.innerHTML = topArtistsHTML;
            errorMessage.style.display = 'none';
        })
        .catch(error => {
            errorMessage.style.display = 'block';
            errorMessage.textContent = 'Error fetching top artists';
            console.error('Error:', error);
        });
}
