class Student
{
     String name;
     String address;

    public void setName(String name) throws IllegaNameException
    {
        int n=name.length();
        if(n<1||n>5)
        {
            throw new IllegaNameException("�������Ȳ�����Ҫ��");
        }
        this.name = name;
    }

    public void setAddress(String address) throws  IllegalAddressException
    {
    	if(!(address.contains("ʡ") || address.contains("��")))
        {
    		throw new IllegalAddressException("��ַ������Ҫ�������ʡ��");
        }
    	this.address = address;
    }
}