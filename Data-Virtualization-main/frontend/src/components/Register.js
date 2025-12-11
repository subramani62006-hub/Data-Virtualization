import React,{useState} from 'react';
import API from '../api';
export default function Register(){
  const [u,setU]=useState(''); const [p,setP]=useState('');
  const submit=async e=>{ e.preventDefault(); const r=await API.post('/user/register',null,{params:{username:u,password:p}}); alert(r.data.status); };
  return <div><h3>Register</h3><input className='input' placeholder='username' value={u} onChange={e=>setU(e.target.value)}/><input className='input' placeholder='password' type='password' value={p} onChange={e=>setP(e.target.value)}/><div style={{marginTop:8}}><button className='btn' onClick={submit}>Register</button></div></div>
}
