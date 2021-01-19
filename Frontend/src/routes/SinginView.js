import React from 'react';
import axios from "axios";
import { useHistory } from 'react-router-dom';

const API_URL = "http://localhost:8080/api/";

export const SinginView = () => {
    const history = useHistory();
    
    const siginIn = ({username, password}) => {
        axios.post(API_URL + "signin", {
            username: username,
            password: password
        }).then((res) => {
            if (res.status == 200) {
                localStorage.setItem("LS_JWT_TOKEN", res.data);
                history.push("/memos");
            }
        }).catch((res) => {
            console.log(res);
        })
    }
    return (
        <div>
            
        </div>
    )
}
