import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { SigninView } from './routes/SigninView';
import { Route, useHistory } from 'react-router-dom';


function App() {

  const history = useHistory();
  const [state, setState] = useState({
    username: null,
    jwtToken: null,
  })

  useEffect(() => {
    const jwtToken = localStorage.getItem("LS_JWT_TOKEN");
    if (jwtToken) {
      localStorage.setItem("LS_JWT_TOKEN");
      setState({...state, jwtToken: jwtToken});
      history.push("/memos");
    } else {
      history.push("/signin");
    }
  }, [])

  const signOut = () => {
    localStorage.removeItem("LS_JWT_TOKEN");
  }
  

  return (

    <div className="App">
      <Route
        path='/signin'
        render={() => <SigninView/>}
        />
    </div>
  );
}

export default App;
