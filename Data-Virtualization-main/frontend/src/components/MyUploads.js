import React,{useEffect,useState} from 'react';
import API from '../api';
export default function MyUploads({onSelectUpload}){
  const [uploads,setUploads]=useState([]);
  const fetch=async ()=>{ const user=localStorage.getItem('user'); if(!user) return; const r=await API.get('/excel/list',{params:{username:user}}); setUploads(r.data||[]); };
  useEffect(fetch,[]);
  return <div><h3>My Uploads</h3>{uploads.length===0 && <div className='small'>No uploads yet</div>}{uploads.map(u=> <div key={u.id} className='list-item' onClick={()=>onSelectUpload(u)}>{u.filename} <div style={{fontSize:12,color:'#6b7280'}}>{new Date(u.uploadedAt).toLocaleString()}</div></div>)}</div>;
}
