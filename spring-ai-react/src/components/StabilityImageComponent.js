import React, { useState } from "react";

function StabilityImageGenerator() {
    const [prompt, setPrompt] = useState('');
    // const [imageJson, setImageJson] = useState([]);
    const [imageDataUrls, setImageDataUrls] = useState([]);

    const generateImage = async () => {
        try {
            const response = await fetch(`http://localhost:8080/generate-image-with-stability?prompt=${prompt}`)
            const jsons = await response.json();
            console.log(jsons);
            console.log(Object.keys(jsons));
            console.log(Object.values(jsons));
            console.log(Object.entries(jsons));
            console.log(jsons.length);
            // console.log(jsons.keys());
            // console.log(jsons.values());

            // setImageJson(jsons)

            const urls = await generateImageDataUrl(jsons);
            console.log(urls);
            setImageDataUrls(urls);

        } catch (error) {
            console.error("Error generating image : ", error)
        }
    };

    const generateImageDataUrl = (jsonObject) => {
        const result = [];
        for (const key in jsonObject) {
            if (Object.prototype.hasOwnProperty.call(jsonObject, key)) {
                const base64Data = jsonObject[key];
                result.push(`data:image/png;base64,${base64Data}`); // Create data URL for each key
                // result[key] = `data:image/png;base64,${base64Data}`; // Create data URL for each key
            }
        }
        return result;
    }

    return (
        <div className="tab-content">
            <h2>Generate Image</h2>
            <input
                type="text"
                value={prompt}
                onChange={(e) => setPrompt(e.target.value)}
                placeholder="Enter prompt for image"
            />
            <button onClick={generateImage}>Generate Image</button>
            

            <div className="image-grid">
                

                {imageDataUrls.map((url, index) => (

                    <img key={index} src={url} alt={`Generated ${index}`} />
                ))}
                {[...Array(4 - imageDataUrls.length)].map((_, index) => (
                    <div key={index + imageDataUrls.length}
                        className="empty-image-slot"></div>
                    ))}
            </div>
        </div>
    );
}

export default StabilityImageGenerator;