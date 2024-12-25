import React, { useState } from 'react';
import './App.css';
import OllamaChatComponent from './components/OllamaChatComponent';
import StabilityImageComponent from './components/StabilityImageComponent';

function App() {
  const [activeTab, setActiveTab] = useState('image-generator');

  const handleTabChange = (tab) => {
    //alert(tab)
    setActiveTab(tab);
  };

  return (
    <div className="App">
      <button className={activeTab === 'image-generator' ? 'active' : ''}
       onClick={() => handleTabChange('image-generator')}>
        Image Generator
        </button>
      <button  className={activeTab === 'chat' ? 'active' : ''}
      onClick={() => handleTabChange('chat')}>
        Ask AI
        </button>
      

        <div>
          {activeTab === 'image-generator' && <StabilityImageComponent/>}
          {activeTab === 'chat' && <OllamaChatComponent/>}
        </div>
    </div>
  );
}

export default App;