import React,{useState} from 'react';
import API from '../api';
export default function UploadExcel({onUploaded}){
  const [file,setFile]=useState(null);
  const submit=async e=>{ e.preventDefault(); const user=localStorage.getItem('user'); if(!user) return alert('Not logged in'); if(!file) return alert('Choose file'); const fd=new FormData(); fd.append('file',file); fd.append('username',user); const r=await API.post('/excel/upload',fd,{headers:{'Content-Type':'multipart/form-data'}}); if(r.data.status==='ok'){ alert('Uploaded'); onUploaded && onUploaded(r.data.uploadId); } else alert('Error '+JSON.stringify(r.data)); };
  return <div><h3>Upload Excel</h3><input className='file-input' type='file' accept='.xls,.xlsx' onChange={e=>setFile(e.target.files[0])}/><div style={{marginTop:8}}><button className='btn' onClick={submit}>Upload</button></div></div>;
}
