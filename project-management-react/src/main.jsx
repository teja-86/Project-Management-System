import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom'; // Import BrowserRouter for routing
import './index.css';
import App from './App.jsx';
import {Provider} from "react-redux";
import {Store} from "@/Redux/Store.js";

createRoot(document.getElementById('root')).render(
    <StrictMode>
        <BrowserRouter> {/* Wrap the App component with BrowserRouter */}

            <Provider store={Store}>
                <App/>
            </Provider>

        </BrowserRouter>
    </StrictMode>
);
