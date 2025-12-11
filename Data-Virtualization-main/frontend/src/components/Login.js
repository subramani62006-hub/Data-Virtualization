import React,{useState} from 'react';
import API from '../api';
export default function Login({onLogin}){
  const [u,setU]=useState(''); const [p,setP]=useState('');
  const submit=async e=>{ e.preventDefault(); const r=await API.post('/user/login',null,{params:{username:u,password:p}}); if(r.data.status==='ok'){ localStorage.setItem('user',u); localStorage.setItem('logged','true'); onLogin(); } else alert('Invalid'); };
  return <div><h3>Login</h3><input className='input' placeholder='username' value={u} onChange={e=>setU(e.target.value)}/><input className='input' placeholder='password' type='password' value={p} onChange={e=>setP(e.target.value)}/><div style={{marginTop:8}}><button className='btn' onClick={submit}>Login</button></div></div>
}
