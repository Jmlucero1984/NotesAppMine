import Cookies from 'js-cookie';

const API_BASE_URL = import.meta.env.VITE_APP_SERVER_URL;
const AuthToken = Cookies.get('AuthToken');


export const requestFetchData = async (endpoint, method, data = null) => {


    if (AuthToken) {
        try {
            const response = await fetch(`${API_BASE_URL}/${endpoint}`, {
                method: method,
                body: data ? JSON.stringify(data) : null,
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${AuthToken}`
                }
            });
            if (!response.ok) {
                // Lanza un error que será capturado por el bloque catch
                console.log(response.status)
                Cookies.remove('AuthToken')
              
            }
            return response;
        }
        catch (error) {
            console.log("CATCH")
            console.error(error)
        }

    }
}


