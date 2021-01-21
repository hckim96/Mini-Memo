import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { SigninView } from './routes/SigninView';
import { Route, useHistory } from 'react-router-dom';
import { SignupView } from './routes/SignupView';
import { MemoView } from './routes/MemoView';


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

  return (

    <div className="App">
      <Route
        path='/signin'
        render={() => <SigninView/>}
        />
      <Route
        path='/signup'
        render={() => <SignupView/>}
        />
      <Route
        exact
        path='/memo'
        render={() => <MemoView/>}
        />
    </div>
  );
}

export default App;
