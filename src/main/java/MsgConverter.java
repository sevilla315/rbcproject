public  interface MsgConverter<F, T>  {
    T convert(F msg);
}
