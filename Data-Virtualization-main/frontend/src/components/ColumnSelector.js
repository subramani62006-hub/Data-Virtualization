import React,{useEffect,useState} from 'react';
import API from '../api';
export default function ColumnSelector({uploadId,onSelect}){
  const [cols,setCols]=useState([]);
  useEffect(()=>{ if(!uploadId) return setCols([]); API.get('/chart/columns',{params:{uploadId}}).then(r=>setCols(r.data||[])); },[uploadId]);
  return <div><h3>Columns</h3>{cols.length===0 && <div className='small'>No columns (upload a file)</div>}{cols.map(c=> <div key={c}><button className='btn' style={{marginTop:8}} onClick={()=>onSelect(c)}>{c}</button></div>)}</div>;
}
